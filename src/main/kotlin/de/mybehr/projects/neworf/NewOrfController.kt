package de.mybehr.projects.neworf

import de.mybehr.projects.model.FileFormat
import de.mybehr.projects.service.CsvFileReader
import tornadofx.Controller

/**
 * @author Tobias Behr
 */
class NewOrfController: Controller() {

    val model: NewOrfModel by inject()

    val view: NewOrfView by inject()

    val csvFileReader: CsvFileReader by di()

    fun loadPeptidesHeader() {
        runAsync {
            csvFileReader.read(model.peptidesFile.value, FileFormat.MaxQuandt)
        } ui { csvFile ->
            view.setPeptidesHeader(csvFile.header);
        }
    }
}