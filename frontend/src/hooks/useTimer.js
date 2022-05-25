import { useState, useEffect, useRef } from "react";

const padNumber = (num, length) => {
  return String(num).padStart(length, "0");
};

const useTimer = (min, sec, timerState) => {
  const tempMin = Number(min);
  const tempSec = Number(sec);

  const initialTime = useRef(min * 60 + sec);
  const interval = useRef(null);

  const [timerMin, setTimerMin] = useState(padNumber(tempMin, 2));
  const [timerSec, setTimerSec] = useState(padNumber(tempSec, 2));

  useEffect(() => {
    if (timerState) {
      interval.current = setInterval(() => {
        initialTime.current = initialTime.current - 1;
        setTimerSec(padNumber(initialTime.current % 60, 2));
        setTimerMin(Math.floor(padNumber(initialTime.current / 60)), 2);
        console.log(initialTime.current);
      }, 1000);
    } else {
      clearInterval(interval.current);
    }
    return () => {
      console.log("끝");
      clearInterval(interval.current);
    };
  }, [timerState]);

  useEffect(() => {
    if (initialTime.current <= 0) {
      clearInterval(interval.current);
    }
  }, [initialTime.current]);

  return { timerMin, timerSec };
};

export default useTimer;
