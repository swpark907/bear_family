import React from "react";
import "./App.scss";
import { Routes, Route } from "react-router-dom";
import Register from "./pages/Register/Register";
import axios from 'axios';

function App() {
  READ();

  return (
    <div className="App">
      <Routes>
        <Route path='register' element={<Register />} />
      </Routes>
    </div>
  );
}

async function READ() {
  var payload;

  await axios.get("/restapi/user").then(
    (Response) => {
      payload = Response.data.payload;
    }
  );

  console.log(payload);
  
  return payload;
}

// function Authenticate(username, password){
//   var token = axios.post('/authenticate', {
//       username,
//       password
//   })
// }

// function SignOn(){

// }

export default App;