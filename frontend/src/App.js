import React from "react";
import "./App.scss";
import { Routes, Route } from "react-router-dom";
import Register from "./pages/Register/Register";
import RegisterSuccess from "./pages/Register/RegisterSuccess";
import Login from "./pages/Login/Login";
import NotFoundPage from "./pages/NotFoundPage/NotFoundPage";
import Home from "./pages/Home/Home";
import Calendar from "./pages/Calendar/Calendar";
import PublicRoutes from "./route/PublicRoutes";
import PrivateRoutes from "./route/PrivateRoutes";
import { Loading } from "./components/common";
import { useSelector } from "react-redux";
import Create from "./components/Create/CreateModal";

function App() {
  const loadingReducer = useSelector((state) => state.loadingReducer);

  return (
    <div className="App">
      <Routes>
        <Route element={<PrivateRoutes />}>
          <Route exact path="home" element={<Home />}></Route>
          <Route path="calendar" element={<Calendar />} />
        </Route>

        <Route element={<PublicRoutes restricted={true} />}>
          <Route path="register">
            <Route path="" element={<Register />} />
            <Route path="success" element={<RegisterSuccess />} />
          </Route>
          <Route path="login" element={<Login />} />
        </Route>

        {/* <Route element={<PublicRoutes restricted={false} />}></Route> */}
        <Route path="create" element={<Create />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
      <Loading state={loadingReducer.isLoading} />
    </div>
  );
}

export default App;