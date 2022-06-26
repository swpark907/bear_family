import React from "react";

const CalendarWeek = () => {
  const Week = ["일", "월", "화", "수", "목", "금", "토"];
  return (
    <ul className="template__weeks">
      {Week.map((week) => (
        <li className="week">{week}</li>
      ))}
    </ul>
  );
};

export default CalendarWeek;
