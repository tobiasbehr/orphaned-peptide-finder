package de.mybehr.projects.neworf

import de.mybehr.projects.model.CsvFile
import javafx.beans.property.Property
import tornadofx.ViewModel
import tornadofx.observable
import java.io.File

/**
 * @author Tobias Behr
 */
class NewOrfModel(var input: NewOrfInput = NewOrfInput()) : ViewModel() {
    val peptidesFile: Property<File> = bind { input.observable(NewOrfInput::peptidesFile)}
    val peptidesColumn: Property<CsvFile.HeaderColumn> = bind { input.observable(NewOrfInput::peptidesColumn)}
    val accessionColumn: Property<CsvFile.HeaderColumn> = bind { input.observable(NewOrfInput::accessionColumn)}
    val referenceDbFile: Property<File> = bind { input.observable(NewOrfInput::referenceDbFile)}
    val targetFolder: Property<File> = bind { input.observable(NewOrfInput::targetFolder)}
}

