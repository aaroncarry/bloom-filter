package process

import java.math.BigInteger

abstract class AbstractHashProcessor {
    abstract fun getHashCode(string: String): HashCodeContext

    protected fun toHex(byteArray: ByteArray): String {
        return with(StringBuilder()) {
            byteArray.forEach {
                val hex = it.toInt() and (0xFF)
                val hexStr = Integer.toHexString(hex)
                if (hexStr.length == 1) {
                    this.append("0").append(hexStr)
                } else {
                    this.append(hexStr)
                }
            }
            this.toString()
        }
    }

    operator fun plus(element: AbstractHashProcessor): AbstractHashProcessor {
        return object : AbstractHashProcessor() {
            override fun getHashCode(string: String): HashCodeContext {
                val hash1 = BigInteger(this@AbstractHashProcessor.getHashCode(string).hashCode, 16)
                val hash2 = BigInteger(element.getHashCode(string).hashCode, 16)
                return HashCodeContext((hash1 + hash2).toString(16))
            }
        }
    }

    operator fun times(times: Int): AbstractHashProcessor {
        return object : AbstractHashProcessor() {
            override fun getHashCode(string: String): HashCodeContext {
                val hash = BigInteger(this@AbstractHashProcessor.getHashCode(string).hashCode, 16)
                return HashCodeContext(hash.times(times.toBigInteger()).toString(16))
            }
        }
    }
}