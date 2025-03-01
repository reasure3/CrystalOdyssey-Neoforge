package com.reasure.crystal_odyssey.item.custom

import com.reasure.crystal_odyssey.util.TranslateHelper
import com.reasure.crystal_odyssey.util.TranslateHelper.translateComponent
import net.minecraft.client.resources.language.I18n
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.LiquidBlockContainer
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.neoforged.neoforge.capabilities.Capabilities
import net.neoforged.neoforge.fluids.FluidActionResult
import net.neoforged.neoforge.fluids.FluidStack
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.fluids.FluidUtil
import net.neoforged.neoforge.fluids.capability.IFluidHandler
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem
import kotlin.math.min

open class CapabilityBucketItem(properties: Properties, private val fluidDescKey: String = "") : Item(properties) {
    // Use the "use" method because the "useOn" method cannot detect the FluidBlock.
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val blockHitResult: BlockHitResult = getPlayerPOVHitResult(
            level, player, if (player.isShiftKeyDown) ClipContext.Fluid.SOURCE_ONLY else ClipContext.Fluid.NONE
        )

        if (blockHitResult.type != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(player.getItemInHand(usedHand))
        }

        val bucket: ItemStack = player.getItemInHand(usedHand)
        val clickedDir: Direction = blockHitResult.direction
        val clickedPos: BlockPos = blockHitResult.blockPos
        val relativePos: BlockPos = clickedPos.relative(clickedDir)

        if (!level.mayInteract(player, clickedPos) || !player.mayUseItemAt(relativePos, clickedDir, bucket)) {
            return InteractionResultHolder.fail(player.getItemInHand(usedHand))
        }

        return if (player.isShiftKeyDown) {
            handleFluidPickup(level, player, usedHand, bucket, clickedPos, clickedDir)
        } else {
            handleFluidPlacement(level, player, usedHand, bucket, clickedPos, clickedDir)
        }
    }

    private fun handleFluidPlacement(
        level: Level, player: Player, usedHand: InteractionHand,
        bucket: ItemStack, clickedPos: BlockPos, clickedDir: Direction
    ): InteractionResultHolder<ItemStack> {
        val bucketFluid: IFluidHandlerItem = bucket.getCapability(Capabilities.FluidHandler.ITEM)
            ?: return InteractionResultHolder.fail(player.getItemInHand(usedHand))

        val bucketFluidStack: FluidStack = bucketFluid.getFluidInTank(0)
        val relativePos: BlockPos = clickedPos.relative(clickedDir)

        if (bucketFluidStack.isEmpty) {
            return InteractionResultHolder.fail(bucket)
        }

        val clickedFluidCap: IFluidHandler? =
            level.getCapability(Capabilities.FluidHandler.BLOCK, clickedPos, clickedDir)
        if (clickedFluidCap != null) {
            val emptyFluidResult: FluidActionResult = FluidUtil.tryEmptyContainer(
                bucket, clickedFluidCap,
                min(bucketFluidStack.amount, FluidType.BUCKET_VOLUME),
                player, true
            )
            if (emptyFluidResult.success) {
                if (!player.isCreative) {
                    player.setItemInHand(usedHand, emptyFluidResult.result)
                }
                level.gameEvent(player, GameEvent.FLUID_PLACE, clickedPos)
                return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide)
            }
        }

        val clickedBlock: BlockState = level.getBlockState(clickedPos)
        val canPlaceOnClickedPos =
            canPlaceLiquidOnLiquidContainer(player, level, clickedPos, clickedBlock, bucketFluidStack.fluid)
        val placePos = if (canPlaceOnClickedPos) clickedPos else relativePos
        val placeFluidResult: FluidActionResult = FluidUtil.tryPlaceFluid(
            player, level, usedHand, placePos, bucket,
            bucketFluidStack.copyWithAmount(min(bucketFluidStack.amount, FluidType.BUCKET_VOLUME))
        )
        if (placeFluidResult.success) {
            if (!player.isCreative) {
                player.setItemInHand(usedHand, placeFluidResult.result)
            }
            level.gameEvent(player, GameEvent.FLUID_PLACE, placePos)
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide)
        }

        return InteractionResultHolder.fail(player.getItemInHand(usedHand))
    }

    private fun handleFluidPickup(
        level: Level, player: Player, usedHand: InteractionHand, bucket: ItemStack,
        clickedPos: BlockPos, clickedDir: Direction
    ): InteractionResultHolder<ItemStack> {
        val drainFluidResult: FluidActionResult =
            FluidUtil.tryPickUpFluid(bucket, player, level, clickedPos, clickedDir)
        if (drainFluidResult.success) {
            player.setItemInHand(usedHand, drainFluidResult.result)
            level.gameEvent(player, GameEvent.FLUID_PICKUP, clickedPos)
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide)
        }

        return InteractionResultHolder.fail(player.getItemInHand(usedHand))
    }

    private fun canPlaceLiquidOnLiquidContainer(
        player: Player,
        level: Level,
        pos: BlockPos,
        blockState: BlockState,
        fluid: Fluid
    ): Boolean {
        val liquidContainer = blockState.block as? LiquidBlockContainer ?: return false
        return liquidContainer.canPlaceLiquid(player, level, pos, blockState, fluid)
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        if (fluidDescKey.isNotEmpty()) {
            tooltipComponents.add(TranslateHelper.Tooltip.Crouch.PICKUP_FLUID.translateComponent(I18n.get(fluidDescKey)))
        }
    }
}