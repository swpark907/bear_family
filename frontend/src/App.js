import React from "react";
import "./App.scss";
import { Routes, Route } from "react-router-dom";
import Register from "./pages/Register/Register";
import RegisterSuccess from "./pages/Register/RegisterSuccess";
import Login from "./pages/Login/Login";
import NotFoundPage from "./pages/NotFoundPage/NotFoundPage";
import Home from "./pages/Home/Home";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route exact path="home" element={<Home />}></Route>
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

export default App;