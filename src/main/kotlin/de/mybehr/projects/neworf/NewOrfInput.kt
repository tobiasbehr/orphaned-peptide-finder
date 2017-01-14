package de.mybehr.projects.neworf

import tornadofx.property
import java.io.File

/**
 * @author Tobias Behr
 */
class NewOrfInput(peptidesFile: File? = null) {
    var peptidesFile by property(peptidesFile)
}