import React, { useState } from 'react';
import axios from 'axios';

function PositiveMessage() {
  const [message, setMessage] = useState('');

  const handleClick = () => {
    axios.post(
      'https://api.sonicAPI.com/chatsonic/v2/business/content/chatsonic',
      {
        input_text: 'Write me a positive message for a man in his 40s struggling with the meaning of life',
        enable_google_results: false,
        enable_memory: false,
      },
      {
        headers: {
          'Authorization': 'Bearer f76516b1-9fd3-46f2-8384-5f51bd83d073',
          'Content-Type': 'application/json',
        },
        params: {
          engine: 'premium',
        },
      }
    )
    .then(response => {
      setMessage(response.data.text);
    })
    .catch(error => {
      console.error(error);
    });
  }

  return (
    <div>
      <h1>Positive message</h1>
      <button onClick={handleClick}>Get message</button>
      {message && <p>{message}</p>}
    </div>
  );
}

export default PositiveMessage;