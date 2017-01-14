package de.mybehr.projects.neworf

import javafx.beans.property.Property
import tornadofx.ViewModel
import tornadofx.observable
import java.io.File

/**
 * @author Tobias Behr
 */
class NewOrfModel(var input: NewOrfInput = NewOrfInput()) : ViewModel() {
    val peptidesFile: Property<File> = bind { input.observable(NewOrfInput::peptidesFile)}
}

