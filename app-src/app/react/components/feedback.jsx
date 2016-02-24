import React, { Component, PropTypes } from 'react'

export class Feedback extends Component {

    render() {
        const { value } = this.props;
        return (
            <form role="form" name="feedbackForm">
                <div className="form-group">
                    <label htmlFor="title">Title:</label>
                    <input type="text" className="form-control" id="title" name="title" required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="username">Username:</label>
                    <input type="text" className="form-control" id="username" name="username" required/>
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email:</label>
                    <input type="email" className="form-control" id="email" name="email" required/>
                </div>
                <div className="form-group">
                    <label htmlFor="content">Content:</label>
                    <textArea rows="6" className="form-control" id="content"></textArea>

                </div>
                <button type="button" className="btn btn-default" onclick>Send</button>
            </form>
        );
    }
}

Feedback.propTypes = {
    value: PropTypes.object.isRequired
};


