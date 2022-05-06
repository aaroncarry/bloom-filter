
class BloomFilterHolder(initContext: InitContext) {

    companion object {
        @Volatile
        private var instance: BloomFilter? = null
        fun getInstance(initContext: InitContext): BloomFilter {
            val i = instance
            if (i != null) {
                return i
            }

            return synchronized(this) {
                val i2 = instance
                if (i2 != null) {
                    i2
                } else {
                    val created = BloomFilter(initContext)
                    instance = created
                    created
                }
            }
        }
    }


}