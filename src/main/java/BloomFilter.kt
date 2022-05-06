import dto.BitMap
import process.HashHandle

class BloomFilter(initContext: InitContext) {
    private val context: InitContext = initContext
    private val bitMap by lazy { BitMap(size = context.size) }
    private val hashHandle = HashHandle(context.hashCount)

    fun mark(element: String) {

    }

    fun isMark(element: String): Boolean {
        TODO()
    }
}