import React, { useState } from "react";
import CalendarHistoryList from "./CalendarHistoryList";
import CalendarHistoryTotal from "./CalendarHistoryTotal";

const CalendarHistory = () => {
  const [total, setTotal] = useState(0);
  return (
    <div className="calendar__history">
      <CalendarHistoryList />
      <CalendarHistoryTotal total={total} />
    </div>
  );
};

export default CalendarHistory;
