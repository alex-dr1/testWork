const rootDiv = document.querySelector('#root')
let state = { route: 0, sec_id: 1 }

function render() {
  if (state.route == 0) {
    findOneSecuritiesById(state.sec_id)
  }
}

const clickHandler = (event) => {
  event.preventDefault()
  const id_element = event.target.id
  if (id_element === 'securities') {
    state.sec_id = event.target.value
    render()
  }

  if (id_element === 'btn_get') {
    let v = document.querySelector('#securities').value
    console.log(v)
    state.sec_id = v
    //console.log(state)
    render()
  }
}

document.addEventListener('click', clickHandler)

render()
