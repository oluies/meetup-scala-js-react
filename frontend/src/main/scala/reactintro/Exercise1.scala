package reactintro

import japgolly.scalajs.react.vdom.all._
import japgolly.scalajs.react._
import org.scalajs.dom._

import scala.scalajs.js.annotation.JSExport

@JSExport
object Exercise1 {

  type State = Boolean
  /**
    * https://github.com/japgolly/scalajs-react/blob/master/doc/USAGE.md#callbacks
    */
  class Backend(scope: BackendScope[Unit, State]) {

    def handleClick(e:ReactEvent):Callback =
      scope.modState(state => !state )

    def onButtonPressed: Callback = {
      // Callback.alert("The button was pressed!") >>
      scope.modState(state => !state )
    }

    def render(state: State) = // ← Accept props, state and/or propsChildren as argument
      div(
        button(
          onClick -->  onButtonPressed,
            classSet(
            "btn" -> true,
            "btn-primary" -> true,
            "active" -> false
          )
        )
        ( if (state == false) "Off" else "On" )
      )
  }

  val ToogleButton = ReactComponentB[Unit]("ToogleButton")
    .initialState(false)
    .renderBackend[Backend]  // ← Use Backend class and backend.render
    .build

  val markup =
      div(
        h3("This should be a toogle button:"),
        ToogleButton()
      )

  ReactDOM.render(
    markup,
    document.getElementById("app")
  )
}
