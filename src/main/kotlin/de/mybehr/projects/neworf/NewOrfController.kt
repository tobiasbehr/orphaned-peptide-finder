package de.mybehr.projects.neworf

import de.mybehr.projects.model.FileFormat
import de.mybehr.projects.service.file.CsvFileReader
import de.mybehr.projects.service.file.FastAFileReader
import tornadofx.Controller

/**
 * @author Tobias Behr
 */
class NewOrfController: Controller() {

    val model: NewOrfModel by inject()

    val view: NewOrfView by inject()

    val csvFileReader: CsvFileReader by di()

    val fastaReader: FastAFileReader by di()

    fun loadPeptidesHeader() {
        runAsync {
            csvFileReader.read(model.peptidesFile.value, FileFormat.MaxQuandt)
        } ui { csvFile ->
            view.setPeptidesHeader(csvFile.header);
        }
    }

    fun loadFastAFile() {
        runAsync {
            fastaReader.read(model.referenceDbFile.value, FileFormat.MaxQuandt)
        } ui {
            fastAFile -> println("Read FastAFile: ${fastAFile.entries.size} entries!")
        }
    }

    fun startAnalysis() {
        println("NewOrfInput: ${model.input}")
    }
}