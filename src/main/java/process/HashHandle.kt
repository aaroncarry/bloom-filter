package process

import exception.BloomFilterException

class HashHandle(hashMethodSize: Int) {
    private val baseHashProcessor = arrayListOf(MD5Processor(), SHA1Processor())

    private val hashList: ArrayList<AbstractHashProcessor> = arrayListOf()

    init {
        when {
            hashMethodSize <= 0 -> throw BloomFilterException("hash method size error")
            hashMethodSize == 1 -> {
                hashList.add(baseHashProcessor.first())
            }
            hashMethodSize == 2 -> {
                hashList.add(baseHashProcessor.first())
                hashList.add(baseHashProcessor.last())
            }
            else -> {
                for (i in 1..hashMethodSize) {
                    hashList.add(baseHashProcessor.first().plus(baseHashProcessor.last().times(i)))
                }
            }
        }
    }

    private fun generateHashCode(text: String): ArrayList<HashCodeContext> {
        val result = arrayListOf<HashCodeContext>()
        hashList.forEach {
            result.add(it.getHashCode(text))
        }
        return result
    }
}