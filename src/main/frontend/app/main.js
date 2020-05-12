import React, { useState, useEffect } from 'react'
import ReactDom from 'react-dom'

import EventForm from './EventForm'

//useState
//where to replace the CRUD
const App = props => {
  const [events, setEvents] = useState([])

  const onEventAdd = event => {
    fetch('/api/v1/events', {
      method: 'POST',
      body: JSON.stringify(event),
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(resp => {
        return resp.json()
      })
      .then(json => {
        setEvents([...events, json])
      })
      .catch(() => {
        alert('Your form submission is invalid')
      })
  }

  const loadEvents = () => {
    fetch('/api/v1/events')
      .then(resp => {
        if (resp.ok) {
          return resp
        } else {
          throw new Error(resp.Error)
        }
      })
      .then(resp => {
        return resp.json()
      })
      .then(body => {
        setEvents(body.content)
      })
  }
  useEffect(loadEvents, [])

  const eventListItems = events.map(event => {
    return (
      <li key={event.id}>
        <h3>
          {event.name} {event.hostingOrganization}
        </h3>
      </li>
    )
  })

  return (
    <div>
      <h1>Active Events</h1>
      <ul>{eventListItems}</ul>
      <EventForm onEventAdd={onEventAdd} />
    </div>
  )
}

ReactDom.render(<App />, document.getElementById('app'))
