import React from "react";
import Header from "../../components/Home/Header";
import Carousel from "../../components/Home/Carousel";
import Target from "../../components/Home/Target";
import HomeHistory from "../../components/Home/HomeHistory";
import useApi from "../../hooks/useApi";

const Home = () => {
  useApi({ url: "/home" });

  return (
    <section className="section-home">
      <Header />
      <Carousel />
      <Target />
      <HomeHistory />
    </section>
  );
};

export default Home;
