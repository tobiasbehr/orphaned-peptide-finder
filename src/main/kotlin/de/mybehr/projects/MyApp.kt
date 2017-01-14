package de.mybehr.projects

import com.google.inject.Guice
import de.mybehr.projects.neworf.NewOrfView
import tornadofx.App
import tornadofx.DIContainer
import tornadofx.FX
import kotlin.reflect.KClass

/**
 * @author Tobias Behr
 */
class MyApp : App(NewOrfView::class) {
        val guice = Guice.createInjector(NewOrfModule())
    init {
        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>)
                    = guice.getInstance(type.java)
        }
    }
}
