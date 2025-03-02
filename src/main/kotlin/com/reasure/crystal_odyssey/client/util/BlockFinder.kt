package com.reasure.crystal_odyssey.client.util

import com.reasure.crystal_odyssey.CrystalOdysseyServerConfig
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import net.minecraft.client.Minecraft
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

object BlockFinder {
    private val _foundBlocks: MutableList<BlockPos> = mutableListOf()
    val foundBlocks: List<BlockPos>
        get() = _foundBlocks

    private var lastFoundTick: Long = 0

    var renderBlock: Boolean = false
        private set

    var borderColor: Int = 0

    fun startFind(level: Level, startPos: BlockPos, findBlocksData: FindBlocks) {
        if (findBlocksData.isNotEmpty()) {
            synchronized(_foundBlocks) {
                _foundBlocks.clear()
                findBlock(level, startPos, findBlocksData)
            }
            lastFoundTick = level.gameTime
            if (_foundBlocks.isNotEmpty()) {
                renderBlock = true
                borderColor = findBlocksData.borderColor.toInt()
            }
        }
    }

    fun removePos(pos: BlockPos) {
        _foundBlocks.remove(pos)
        if (_foundBlocks.isEmpty()) {
            clearFind()
        }
    }

    fun clearFind() {
        _foundBlocks.clear()
        lastFoundTick = 0
        renderBlock = false
    }

    fun onClientTick() {
        if (!renderBlock) return
        val minecraft = Minecraft.getInstance() ?: return
        val currentTick = minecraft.level?.gameTime ?: return
        val borderDuration = CrystalOdysseyServerConfig.SERVER.elDoradoStaffRetainTick
        if (currentTick - lastFoundTick >= borderDuration) {
            clearFind()
        }
    }

    private fun findBlock(
        level: Level,
        startPos: BlockPos,
        findBlocksData: FindBlocks
    ) {
        val range = CrystalOdysseyServerConfig.SERVER.elDoradoStaffRange
        BlockPos.betweenClosed(
            startPos.x - range, startPos.y - range, startPos.z - range,
            startPos.x + range, startPos.y + range, startPos.z + range
        ).forEach {
            val state = level.getBlockState(it)
            if (state.`is`(findBlocksData.blocks)) {
                _foundBlocks.add(it.immutable())
            }
        }
    }
}