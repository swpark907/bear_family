import React from "react";

const CalendarHistoryTotal = ({ total }) => {
  return (
    <div className="history__total">
      <span>계</span>
      <span>
        {total >= 0 ? "+ " : "- "} {total}원
      </span>
    </div>
  );
};

export default CalendarHistoryTotal;
