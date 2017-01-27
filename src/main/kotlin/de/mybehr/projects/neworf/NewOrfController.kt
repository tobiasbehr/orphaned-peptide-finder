package de.mybehr.projects.neworf

import de.mybehr.projects.model.AnalysisFinishedEvent
import de.mybehr.projects.model.FileFormat
import de.mybehr.projects.service.analysis.NewOrfService
import de.mybehr.projects.service.file.CsvFileReader
import de.mybehr.projects.service.file.FastAFileReader
import tornadofx.Controller

/**
 * @author Tobias Behr
 */
class NewOrfController: Controller() {

    val model: NewOrfModel by inject()

    val csvFileReader: CsvFileReader by di()

    val fastaReader: FastAFileReader by di()

    fun loadPeptidesHeader() = csvFileReader.read(model.peptidesFileReference.value, FileFormat.MaxQuandt)

    fun loadFastAFile() = fastaReader.read(model.referenceDbFileReference.value, FileFormat.MaxQuandt)

    fun startAnalysis() {
        println("NewOrfInput: ${model.input}")

        runAsync {
            NewOrfService(model.input).findNewPeptides()
        } ui {
            fire(AnalysisFinishedEvent)
        }
    }
}