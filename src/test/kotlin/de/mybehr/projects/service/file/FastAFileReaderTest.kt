package de.mybehr.projects.service.file

import org.assertj.core.api.Assertions.assertThat
import org.biojava.nbio.core.sequence.ProteinSequence
import org.junit.Test

/**
 * @author Tobias Behr
 */
class FastAFileReaderTest {

    val reader : FastAFileReader = FastAFileReader()

    @Test
    fun shouldExtractFastAEntriesFromResult() {
        val fastAEntries = reader.extractFastAEntries(linkedMapOf("Header1" to ProteinSequence("AAAAA"), "Header2" to ProteinSequence("DDDDD")))

        assertThat(fastAEntries).hasSize(2)
        assertThat(fastAEntries[0].header).isEqualTo("Header1")
        assertThat(fastAEntries[0].sequence).isEqualTo("AAAAA")
        assertThat(fastAEntries[1].header).isEqualTo("Header2")
        assertThat(fastAEntries[1].sequence).isEqualTo("DDDDD")
    }

    @Test
    fun shouldNotFailOnEmptyMap() {
        val fastAEntries = reader.extractFastAEntries(linkedMapOf())

        assertThat(fastAEntries).isEmpty()
    }
}