import { useState, useEffect, useRef } from "react";

const padNumber = (num, length) => {
  return String(num).padStart(length, "0");
};

const useTimer = (min, sec, timerState, setTimerState, errMsgHandler) => {
  const tempMin = Number(min);
  const tempSec = Number(sec);

  const initialTime = useRef(min * 60 + sec);
  const interval = useRef(null);

  const [timerMin, setTimerMin] = useState(padNumber(tempMin, 1));
  const [timerSec, setTimerSec] = useState(padNumber(tempSec, 2));

  useEffect(() => {
    initialTime.current = min * 60 + sec;
    if (timerState) {
      interval.current = setInterval(() => {
        initialTime.current = initialTime.current - 1;
        setTimerSec(padNumber(initialTime.current % 60, 2));
        setTimerMin(Math.floor(padNumber(initialTime.current / 60)), 2);
      }, 1000);
    } else {
      clearInterval(interval.current);
    }
    return () => {
      clearInterval(interval.current);
    };
  }, [timerState]);

  useEffect(() => {
    if (initialTime.current <= 0) {
      errMsgHandler("timeOut");
      clearInterval(interval.current);
      setTimerState(false);
    }
  }, [initialTime.current]);

  return { timerMin, timerSec, interval };
};

export default useTimer;
