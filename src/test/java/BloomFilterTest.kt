import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BloomFilterTest {
    lateinit var context:InitContext
    private val bloomFilter:BloomFilter
        get() = BloomFilterHolder.getInstance(context)

    @Before
    fun setUp(){
        context = InitContext(5000,10)
        BloomFilterHolder.getInstance(context)
    }


    @Test
    fun testCases1(){
        bloomFilter.mark("Test1")
        bloomFilter.mark("Test2")
        bloomFilter.mark("Test3")
        bloomFilter.mark("Test4")
        bloomFilter.mark("Test1")

        Assert.assertTrue(bloomFilter.isMark("Test1"))
        Assert.assertTrue(bloomFilter.isMark("Test2"))
        Assert.assertFalse(bloomFilter.isMark("Test22"))
    }
}