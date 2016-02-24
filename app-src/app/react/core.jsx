import React from 'react'
import ReactDOM from 'react-dom'
import {List} from 'immutable';
import {Feedback} from './feedback';

let myReactApp = {};


function render() {
    ReactDOM.render(
        < Feedback />,
        document.getElementById('root')
    );
}

render();
