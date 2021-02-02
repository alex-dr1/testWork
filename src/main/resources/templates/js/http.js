const securitiesOneCard = (securities) => {
  return `
    <div style="background: aliceblue; padding: 3px; border: #9e9e9e  solid 1px; margin-top: 3px">
        <div><b>id: </b>${securities.id}</div>
        <div><b>secId: </b>${securities.secId}</div>
        <div><b>regNumber: </b>${securities.regNumber}</div>
        <div><b>name: </b>${securities.name}</div>
        <div><b>emitentTitle: </b>${securities.emitentTitle}</div>
    </div>
  `
}

const faultCard = (dataFault) => {
  return `
    <div style="background: #ffcaca; padding: 3px; border: red solid 1px; margin-top: 3px">
        <div>Fault: ${dataFault.status} ${dataFault.httpStatus} ${dataFault.message}</div>
    </div>
    `
}

function ok(data) {
  const toHTML = securitiesOneCard(data)
  rootDiv.innerHTML = toHTML
}

function fault(data) {
  var divFault = document.createElement('div')
  divFault.setAttribute('id', 'errMessage')
  divFault.innerHTML = faultCard(data)
  document.body.insertBefore(divFault, rootDiv)
  setTimeout(() => {
    divFault.remove()
  }, 3000)
}

function findOneSecuritiesById(id) {
  fetch(`http://localhost:8080/api/securities/${id}`)
    .then((response) => {
      if (response.ok) {
        response.json().then((data) => ok(data))
      }
      if (response.status >= 400 && response.status < 500) {
        response.json().then((data) => fault(data))
      }
    })

    .catch(function (error) {
      var p = document.createElement('p')
      p.appendChild(document.createTextNode('Error: ' + error.message))
      document.body.insertBefore(p)
    })
}
