package dk.aau.bio.opf.service.analysis

import dk.aau.bio.opf.model.CsvFile
import dk.aau.bio.opf.model.FastAFile
import dk.aau.bio.opf.neworf.NewOrfInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * @author Tobias Behr
 */
class NewOrfServiceTest {

    @Test
    fun transformToPeptideInput() {
        val newOrfService = NewOrfService(NewOrfInput(null, CsvFile.HeaderColumn("peptide", 1), CsvFile.HeaderColumn("accession", 2)))

        val peptideInput = newOrfService.toPeptidesInput(arrayOf("any", "peptide", "accession"))
        assertThat(peptideInput.peptide).isEqualTo("peptide")
        assertThat(peptideInput.accession).isEqualTo("accession")
    }

    @Test
    fun isInFastAEntries() {
        val newOrfService = NewOrfService(NewOrfInput(null, CsvFile.HeaderColumn("peptide", 1), CsvFile.HeaderColumn("accession", 2)))

        val fastAEntries = listOf(FastAFile.FastAEntry("header1", "ABCDEFG"), FastAFile.FastAEntry("header2", "HIJKLMN"), FastAFile.FastAEntry("header3", "OPQRSTU"))

        assertThat(newOrfService.isInFastAEntries("DEF", fastAEntries)).isTrue()
        assertThat(newOrfService.isInFastAEntries("XYZ", fastAEntries)).isFalse()
    }
}