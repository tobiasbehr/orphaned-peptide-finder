package de.mybehr.projects

import com.google.inject.Guice
import de.mybehr.projects.neworf.NewOrfView
import javafx.stage.Stage
import tornadofx.App
import tornadofx.DIContainer
import tornadofx.FX
import kotlin.reflect.KClass

/**
 * @author Tobias Behr
 */
class OrphanPeptideFinderApp : App(NewOrfView::class) {

    val guice = Guice.createInjector(NewOrfModule())

    override fun start(stage: Stage) {
        stage.isResizable = false
        super.start(stage)
    }

    init {
        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>)
                    = guice.getInstance(type.java)
        }
    }
}
