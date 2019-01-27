import React from 'react';
import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink,
    Container
} from 'reactstrap';
import {isLoggedIn} from "../utils/authUtils";
import PropTypes from 'prop-types';
import { withRouter } from "react-router";
import {connect} from 'react-redux';
import{signOutUser} from '../actions/authActions';

class AppNavBar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false
        };
    }

    toggle = () => {
        this.setState({
            isOpen: !this.state.isOpen
        });
    };

    render() {
        return (
            <div>
                <Navbar color="dark" dark expand="sm" className="mb-5">
                    <Container>
                        <NavbarBrand href="/">SimplePOS</NavbarBrand>
                        <NavbarToggler onClick={this.toggle}/>
                        <Collapse isOpen={this.state.isOpen} navbar>
                            <Nav className="ml-auto" navbar>
                                <NavItem>
                                    {isLoggedIn() ? (
                                        <NavLink onClick={this.props.signOutUser}>Sign Out</NavLink>
                                    ) : (
                                        <NavLink href="/signin">Sign In</NavLink>
                                    )}
                                </NavItem>
                            </Nav>
                        </Collapse>
                    </Container>
                </Navbar>
            </div>
        );
    }
}

AppNavBar.propTypes = {
    authReducer: PropTypes.object.isRequired,
    signOutUser: PropTypes.func.isRequired
};

const mapStateToProps = (state) => ({
    authReducer: state.authReducer
});

export default withRouter(connect(
    mapStateToProps,
    {signOutUser})(AppNavBar));