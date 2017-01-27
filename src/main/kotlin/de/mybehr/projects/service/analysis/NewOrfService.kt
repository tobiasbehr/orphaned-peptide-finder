package de.mybehr.projects.service.analysis

import de.mybehr.projects.model.FastAFile
import de.mybehr.projects.neworf.NewOrfInput

/**
 * @author Tobias Behr
 */
class NewOrfService(val input: NewOrfInput) {

    internal val toPeptidesInput: (Array<String>) -> PeptideInput = { row -> PeptideInput(row[input.peptidesColumn.index], row[input.accessionColumn.index])}

    internal val isInFastAEntries: (String, List<FastAFile.FastAEntry>) -> Boolean = { peptide, fastAEntries -> fastAEntries.filter { it.sequence.contains(peptide) }.any()}

    fun findNewPeptides() : List<Array<String>> {
        val peptideInputs: List<PeptideInput>? = input.peptidesFile?.content?.map(toPeptidesInput)
        val fastAEntries: List<FastAFile.FastAEntry>? = input.fastAFile?.entries

        return peptideInputs?.
                filterNot { isInFastAEntries.invoke(it.peptide, fastAEntries!!) }?.
                map { p -> arrayOf(p.peptide, p.accession) }?.
                toList().orEmpty()
    }

    internal data class PeptideInput(val peptide: String, val accession: String)
}