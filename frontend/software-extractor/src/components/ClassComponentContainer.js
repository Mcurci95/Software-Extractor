import React from 'react';
import {useSelector} from 'react-redux'

import {selectEntity} from '../reducers/entityReducer'
import ClassComponent from './ClassComponent'


export default function ClassComponentContainer() {
  const entities = useSelector(selectEntity);

  return (
    <>
      {entities.map(entity => <ClassComponent key={entity.id} {...entity} />)} 
    </>
  )

}