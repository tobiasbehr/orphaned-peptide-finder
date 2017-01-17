package de.mybehr.projects.service.analysis

import de.mybehr.projects.model.CsvFile
import de.mybehr.projects.model.FastAFile
import de.mybehr.projects.neworf.NewOrfInput
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