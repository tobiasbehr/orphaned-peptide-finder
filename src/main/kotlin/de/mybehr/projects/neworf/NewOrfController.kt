package de.mybehr.projects.neworf

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
            println("This is async")
            csvFileReader.read(model.peptidesFile.value)
        } ui { csvFile ->
            view.setPeptidesHeader(csvFile.header.asList());
        }
    }
}