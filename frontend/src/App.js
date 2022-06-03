import React from "react";
import "./App.scss";
import { Routes, Route } from "react-router-dom";
import Register from "./pages/Register/Register";
<<<<<<< HEAD
import axios from 'axios';
=======
import RegisterSuccess from "./pages/Register/RegisterSuccess";
import Login from "./pages/Login/Login";
<<<<<<< HEAD
>>>>>>> 95f38f6 (#2-0 [Update] 로그인 라우터 추가)
=======
import NotFoundPage from "./pages/NotFoundPage/NotFoundPage";
>>>>>>> 00926de (#9-0 [Add] NotFoundPage 라우팅 추가)

function App() {
  READ();

  return (
    <div className="App">
      <Routes>
        <Route path="register">
          <Route path="" element={<Register />} />
          <Route path="success" element={<RegisterSuccess />} />
        </Route>
        <Route path="login" element={<Login />} />
        <Route path="*" element={<NotFoundPage />} />
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

function SignOn(){

}

export default App;