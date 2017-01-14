package de.mybehr.projects.neworf

import de.mybehr.projects.model.CsvFile
import de.mybehr.projects.model.FileFormat
import de.mybehr.projects.service.FileReader
import tornadofx.Controller

/**
 * @author Tobias Behr
 */
class NewOrfController: Controller() {

    val model: NewOrfModel by inject()

    val view: NewOrfView by inject()

    val csvFileReader: FileReader<CsvFile> by di()

    fun loadPeptidesHeader() {
        runAsync {
            csvFileReader.read(model.peptidesFile.value, FileFormat.MaxQuandt)
        } ui { csvFile ->
            view.setPeptidesHeader(csvFile.header);
        }
    }
}