import dto.BitMap
import process.HashHandle
import java.math.BigInteger

class BloomFilter(initContext: InitContext) {
    private val context: InitContext = initContext
    private val bitMap by lazy { BitMap(size = context.size) }
    private val hashHandle = HashHandle(context.hashCount)

    fun mark(element: String) {
        val hashList = hashHandle.generateHashCode(element)
        hashList.forEach { hashContext ->
            bitMap.setMarked((BigInteger(hashContext.hashCode, 16) % (context.size.toBigInteger())).toInt())
        }
    }

    fun isMark(element: String): Boolean {
        val hashList = hashHandle.generateHashCode(element)
        hashList.forEach { hashContext ->
            if (!bitMap.isMarked((BigInteger(hashContext.hashCode, 16) % (context.size.toBigInteger())).toInt())) return false
        }
        return true
    }
}