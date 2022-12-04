import Link from "next/link";
import { useRouter } from "next/router";
import { Button, Container, Nav, Navbar } from "react-bootstrap";
import { useRecoilValue } from "recoil";
import { userState, loginState } from "../stores/user-store";

export function MenuBar() {
  const { pathname } = useRouter();
  const user = useRecoilValue(userState);
  const isLogin = useRecoilValue(loginState);

  return (
    <Navbar expand="md" bg="dark" variant="dark" sticky="top" collapseOnSelect>
      <Container fluid>
        <Navbar.Brand>cshare</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbar_nav" />
        <Navbar.Collapse id="navbar_nav">
          <Nav className="me-auto mb-3 mb-md-0">
            <Nav.Item className="ms-md-3">
              <Link href="/" className="text-decoration-none">
                <Nav.Link as="span" active={pathname === "/"}>
                  All codes
                </Nav.Link>
              </Link>
            </Nav.Item>
            {isLogin ? (
              <>
                <Nav.Item className="ms-md-3">
                  <Link href="/me" className="text-decoration-none">
                    <Nav.Link as="span" active={pathname === "/me"}>
                      My codes
                    </Nav.Link>
                  </Link>
                </Nav.Item>
                <Nav.Item className="ms-md-3">
                  <Link href="/new" className="text-decoration-none">
                    <Nav.Link as="span" active={pathname === "/new"}>
                      New code
                    </Nav.Link>
                  </Link>
                </Nav.Item>
              </>
            ) : null}
          </Nav>
          {isLogin ? (
            <>
              <Navbar.Text className="me-3">{user.username}</Navbar.Text>
              <Button variant="secondary" className="me-md-4">
                Logout
              </Button>
            </>
          ) : (
            <>
              <Link href="/login">
                <Button variant="primary" className="me-3">
                  Login
                </Button>
              </Link>
              <Link href="/signup">
                <Button variant="success" className="me-md-4">
                  Sign up
                </Button>
              </Link>
            </>
          )}
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
