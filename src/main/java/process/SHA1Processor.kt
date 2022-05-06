package process

import java.security.MessageDigest

class SHA1Processor: AbstractHashProcessor() {
    override fun getHashCode(string: String): HashCodeContext {
        val digest = MessageDigest.getInstance("SHA-1")
        val result = digest.digest(string.toByteArray())
        return HashCodeContext(toHex(result))
    }
}