package dto

import exception.BloomFilterException

class BitMap(size: Int) {
    private val bitArr by lazy { IntArray(size / 32 + 1) }

    fun isMarked(index: Int): Boolean {
        if (index / 32 !in bitArr.indices) throw BloomFilterException("mark bit map is out of index:$index")
        return (bitArr[index / 32] and (1 shl index % 32)) == (1 shl index % 32)
    }

    fun setMarked(index: Int) {
        bitArr[index / 32] = bitArr[index / 32] or (1 shl index % 32)
    }
}