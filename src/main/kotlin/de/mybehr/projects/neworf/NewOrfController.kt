package de.mybehr.projects.neworf

import de.mybehr.projects.model.AnalysisFinishedEvent
import de.mybehr.projects.model.CsvFile
import de.mybehr.projects.model.FileFormat
import de.mybehr.projects.service.analysis.NewOrfService
import de.mybehr.projects.service.file.CsvFileHandler
import de.mybehr.projects.service.file.FastAFileHandler
import tornadofx.Controller

/**
 * @author Tobias Behr
 */
class NewOrfController: Controller() {

    val model: NewOrfModel by inject()

    val csvFileHandler: CsvFileHandler by di()

    val fastaHandler: FastAFileHandler by di()

    fun loadPeptidesHeader() = csvFileHandler.read(model.peptidesFileReference.value, FileFormat.MaxQuandt)

    fun loadFastAFile() = fastaHandler.read(model.referenceDbFileReference.value, FileFormat.MaxQuandt)

    fun startAnalysis() {
        println("NewOrfInput: ${model.input}")

        runAsync {
            val newPeptides = NewOrfService(model.input).findNewPeptides()
            csvFileHandler.write(CsvFile(listOf(CsvFile.HeaderColumn("Peptide", 0), CsvFile.HeaderColumn("Accession", 1)), newPeptides), FileFormat.MaxQuandt, model.targetFolder.value)
        } ui {
            fire(AnalysisFinishedEvent)
        }
    }
}