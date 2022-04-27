import React from "react";
import "./App.scss";
import { Routes, Route } from "react-router-dom";
import Register from "./pages/Register/Register";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path='register' element={<Register />} />
      </Routes>
    </div>
  );
}

export default App;
