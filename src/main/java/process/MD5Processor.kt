package process

import java.security.MessageDigest

class MD5Processor : AbstractHashProcessor() {
    override fun getHashCode(string: String): HashCodeContext {
        val digest = MessageDigest.getInstance("MD5")
        val result = digest.digest(string.toByteArray())
        return HashCodeContext(toHex(result))
    }
}