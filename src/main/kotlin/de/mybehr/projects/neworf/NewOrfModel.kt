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
    val peptidesFileReference: Property<File> = bind { input.observable(NewOrfInput::peptidesFileReference)}
    val peptidesColumn: Property<CsvFile.HeaderColumn> = bind { input.observable(NewOrfInput::peptidesColumn)}
    val accessionColumn: Property<CsvFile.HeaderColumn> = bind { input.observable(NewOrfInput::accessionColumn)}
    val referenceDbFileReference: Property<File> = bind { input.observable(NewOrfInput::referenceDbFileReference)}
    // TODO will we have other file types than FastA files?
    val targetFolder: Property<File> = bind { input.observable(NewOrfInput::targetFolder)}
}

