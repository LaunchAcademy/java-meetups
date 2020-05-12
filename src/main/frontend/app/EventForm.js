import React, { useState, useEffect } from 'react'

const EventForm = props => {
  const defaultNewEvent = {
    name: '',
    hostingOrganization: '',
    categoryId: null
  }

  const handleChange = e => {
    let value = e.currentTarget.value
    if (e.currentTarget.id === 'categoryId' && !!value) {
      value = parseInt(e.currentTarget.value)
    }
    setEvent({
      ...event,
      [e.currentTarget.id]: value
    })
  }

  const handleSubmit = e => {
    e.preventDefault()
    props.onEventAdd(event)
    setEvent(defaultNewEvent)
  }

  const getCategories = () => {
    fetch('/api/v1/categories')
      .then(resp => {
        return resp.json()
      })
      .then(json => {
        setCategories(json.content)
      })
  }

  const [event, setEvent] = useState(defaultNewEvent)
  const [categories, setCategories] = useState([])
  useEffect(getCategories, [])

  const categoryOptions = categories.map(category => {
    return (
      <option key={category.id} value={category.id}>
        {category.name}
      </option>
    )
  })

  return (
    <form onSubmit={handleSubmit}>
      <h2>Add a New Event</h2>
      <div>
        <label htmlFor="name">Name</label>
        <input type="text" name="name" id="name" onChange={handleChange} />
      </div>
      <div>
        <label htmlFor="hostingOrganization">Hosting Organization</label>
        <input
          type="text"
          name="hostingOrganization"
          id="hostingOrganization"
          onChange={handleChange}
        />
      </div>
      <div>
        <label htmlFor="categoryId">Category</label>
        <select name="categoryId" id="categoryId" onChange={handleChange}>
          <option value="" />
          {categoryOptions}
        </select>
      </div>

      <div>
        <input type="submit" value="Add Event" />
      </div>
    </form>
  )
}

export default EventForm
