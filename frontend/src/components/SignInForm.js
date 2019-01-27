import React from 'react';
import { Col, Button, Form, FormGroup, Label, Input, Container } from 'reactstrap';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { authenticateUser } from "../actions/authActions";
import { withRouter } from "react-router";

class SignInForm extends React.Component {

    addToState = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    };

    submitForm = (e) => {
        e.preventDefault();

        let user = {
            username: this.state.username,
            password: this.state.password
        };

        this.props.authenticateUser(user, () => {
            this.props.history.push("/");
        });
    };

    render() {
        return (
            <Container>
                <Col md={{ size: 6, offset: 3 }}>
                    <Form onSubmit={this.submitForm}>
                        <h2>Sign In</h2>
                        <hr />
                        <FormGroup row>
                            <Label for="username">Username</Label>
                            <Input type="text" name="username" id="username" placeholder="your username"
                                onChange={this.addToState} autoFocus required />
                        </FormGroup>
                        <FormGroup row>
                            <Label for="examplePassword">Password</Label>
                            <Input type="password" name="password" id="password" placeholder="your password"
                                onChange={this.addToState} required />
                        </FormGroup>
                        <FormGroup check row>
                            <Col md={{ offset: 5 }}>
                                <Button color="primary" size="lg">Login</Button>
                            </Col>
                        </FormGroup>
                    </Form>
                </Col>
            </Container>
        );
    }
}

SignInForm.propTypes = {
    authReducer: PropTypes.object.isRequired,
    authenticateUser: PropTypes.func.isRequired
};

const mapStateToProps = (state) => ({
    authReducer: state.authReducer
});

export default withRouter(connect(
    mapStateToProps,
    { authenticateUser })(SignInForm));