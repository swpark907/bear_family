import React from "react";
import { useState, useEffect, useRef } from "react";
import { Button } from "../common";

const Carousel = () => {
  const TOTAL_SLIDE = 4;

  const [currentSlideIndex, setCurrentSlideIndex] = useState(0);

  const slideRef = useRef();
  const slideDotRef = useRef();

  const slideToTarget = ({ target }) => {
    setCurrentSlideIndex(target.id);
  };

  useEffect(() => {
    slideRef.current.style.transform = `translateX(-${currentSlideIndex}00%)`;
    const dot = slideDotRef.current.childNodes;
    dot.forEach((d) => {
      if (Number(d.id) == currentSlideIndex) {
        d.classList.add("active");
      } else {
        d.classList.remove("active");
      }
    });    
  }, [currentSlideIndex]);

  const slideLists = [];

  return (
    <div className="home__carousel">
      <div className="carousel__container" ref={slideRef}>
        <div className="carousel">첫번째 캐러셀</div>
        <div className="carousel">두번째 캐러셀</div>
        <div className="carousel">세번째 캐러셀</div>
        <div className="carousel">네번째 캐러셀</div>
      </div>
      <ul className="carousel__dot-nav" ref={slideDotRef}>
        {[...Array(TOTAL_SLIDE)].map((el, index) => (
          <li
            className={"dot-nav__dot"}
            id={index}
            key={index}
            onClick={slideToTarget}
          ></li>
        ))}
      </ul>
    </div>
  );
};

export default Carousel;
