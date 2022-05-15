import React, { useEffect, useState } from 'react';

const Title = ({variant, children, className}) => {

  const TitlePrimary = (content, className) => (
    <h2 className={(className?className:'') + (" ") + variant}>{content}</h2>
  )

  const TitleSecondary = (content, className) => (
    <h3 className={(className?className:'') + (" ") + variant}>{content}</h3>
  )

  const TitleTertiary = (content, className) => (
    <h4 className={(className?className:'') + (" ") + variant}>{content}</h4>
  )

  const [titleInner, setTitleInner] = useState('')

  const titleTypeHandler = (variant) => {
    switch(variant){
      case "primary":
        setTitleInner(TitlePrimary(children, className))
        return;
      case "secondary":
        setTitleInner(TitleSecondary(children, className))
        return;
      case "tertiary":
        setTitleInner(TitleTertiary(children, className))
      default:
        return;
    }
  }

  useEffect(() => {titleTypeHandler(variant)}, [])

  return (
    <>
      {titleInner}
    </>
  );
};

export default Title;