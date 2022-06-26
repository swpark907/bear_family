import React from "react";
import CalendarDate from "./CalendarDate";
import CalendarWeek from "./CalendarWeek";

const CalendarTemplate = () => {
  return (
    <div className="calendar__template">
      <CalendarWeek></CalendarWeek>
      <CalendarDate></CalendarDate>
    </div>
  );
};

export default CalendarTemplate;
