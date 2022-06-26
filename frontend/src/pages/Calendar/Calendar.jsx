import React from "react";
import CalendarTemplate from "../../components/Calendar/CalendarTemplate";
import CalendarHistory from "../../components/Calendar/CalendarHistory";
import CalendarUsage from "../../components/Calendar/CalendarUsage";

const Calendar = () => {

  

  return (
    <section className="section-calendar">
      캘린더에요 header(navigation)
      <CalendarUsage />
      <CalendarTemplate />
      <CalendarHistory />
    </section>
  );
};

export default Calendar;
