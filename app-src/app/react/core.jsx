import React from 'react';
import ReactDOM from 'react-dom';
import {createStore, applyMiddleware} from 'redux';
import createLogger from 'redux-logger';
import {List} from 'immutable';
import {Feedback} from './components/feedback';


const logger = createLogger();


var feedbackNumber = 0;

const INITIAL_FEEDBACK_FORM = {
    feedback: {
        id: 0, title: "Вопрос", username: "", email: "", content: "", status: "DRAFT"
    },
    feedbackList: new List()
};


function reducer(state = INITIAL_FEEDBACK_FORM, action) {
    switch (action.type) {
        case 'SEND_FEEDBACK':
            let sendingFeedback = Object.assign({}, state, {id: feedbackNumber++, status: "SENDING"});
            return {
                feedback: INITIAL_FEEDBACK_FORM,
                feedbackList: state.feedbackList.concat(List.of(sendingFeedback), state.feedbackList)
            };
        default:
            return state
    }
}

const store = createStore(reducer, applyMiddleware(logger));

function render() {
    ReactDOM.render(
        < Feedback
            value={store.getState().feedback}
           />,

        document.getElementById('root')
    );
}

render();

store.subscribe(render);
