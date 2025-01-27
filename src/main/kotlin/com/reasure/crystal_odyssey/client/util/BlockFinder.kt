package com.reasure.crystal_odyssey.client.util

import com.reasure.crystal_odyssey.CrystalOdysseyServerConfig
import com.reasure.crystal_odyssey.item.components.custom.FindBlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.phys.AABB

object BlockFinder {
    private var _foundBlocks: List<AABB> = listOf()
    val foundBlocks: List<AABB>
        get() = _foundBlocks

    var lastFoundTick: Long = 0
        private set

    var renderBlock: Boolean = false

    var borderColor: Int = 0

    fun startFind(level: Level, startPos: BlockPos, findBlocksData: FindBlocks) {
        if (findBlocksData.isNotEmpty()) {
            synchronized(_foundBlocks) {
                val blockPosList = findBlock(level, startPos, findBlocksData)
                _foundBlocks = calculateVisibleEdges(blockPosList)
            }
            lastFoundTick = level.gameTime
            if (_foundBlocks.isNotEmpty()) {
                renderBlock = true
                borderColor = findBlocksData.borderColor.toInt()
            }
        }
    }

    fun clearFind() {
        _foundBlocks = listOf()
        lastFoundTick = 0
        renderBlock = false
    }

    private fun findBlock(level: Level, startPos: BlockPos, findBlocksData: FindBlocks): List<BlockPos> {
        val range = CrystalOdysseyServerConfig.SERVER.elDoradoStaffRange
        val list: MutableList<BlockPos> = mutableListOf()
        BlockPos.betweenClosed(
            startPos.x - range, startPos.y - range, startPos.z - range,
            startPos.x + range, startPos.y + range, startPos.z + range
        ).forEach {
            if (level.getBlockState(it).`is`(findBlocksData.blocks)) {
                list.add(it.immutable())
            }
        }
        return list.toList()
    }

    // TODO: 무조건 직욱면체 모양에서 다양한 모양으로 바꾸기
    private fun calculateVisibleEdges(blocks: List<BlockPos>): List<AABB> {
        val visited = mutableSetOf<BlockPos>()
        val result = mutableListOf<AABB>()

        for (block in blocks) {
            if (block in visited) continue

            // 연결된 블록 그룹 찾기 (Flood Fill 알고리즘)
            val group = mutableListOf<BlockPos>()
            val queue = ArrayDeque<BlockPos>()
            queue.add(block)

            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()
                if (current in visited) continue

                visited.add(current)
                group.add(current)

                // 인접한 블록 추가 (6면 탐색)
                val neighbors = listOf(
                    current.north(), current.south(), current.east(),
                    current.west(), current.above(), current.below()
                )
                neighbors.forEach {
                    if (it in blocks && it !in visited) {
                        queue.add(it)
                    }
                }
            }

            // 그룹 경계 상자 계산
            val minX = group.minOf { it.x }.toDouble()
            val minY = group.minOf { it.y }.toDouble()
            val minZ = group.minOf { it.z }.toDouble()
            val maxX = group.maxOf { it.x + 1 }.toDouble()
            val maxY = group.maxOf { it.y + 1 }.toDouble()
            val maxZ = group.maxOf { it.z + 1 }.toDouble()

            result.add(AABB(minX, minY, minZ, maxX, maxY, maxZ))
        }

        return result.toList()
    }
}