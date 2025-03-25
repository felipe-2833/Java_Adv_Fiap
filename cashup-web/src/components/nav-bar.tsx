import Link from "next/link";

// criar tipo da propriedade (será passado no page)
interface NavBarProps {
    active: "dashboard" | "movimentações" | "categorias"
}

export default function NavBar(props: NavBarProps){

    const {active} = props
    const activeClass = "border-b-4 border-teal-500 pb-1"

    const links = [
        {text: "dashboard", href:"/dashboard"},
        {text: "movimentações", href:"/transactions"},
        {text: "categorias", href:"/categories"},
        
    ]

    return (
        <nav className="flex justify-between items-center bg-slate-900 p-6">
            <h1 className="text-3xl font-bold">CashUP</h1>
            <ul className="flex gap-4 ">
            {links.map(link => 
                        <li className={active === link.text ? activeClass : ""}>
                            <Link href= {link.href}>{link.text}</Link>
                        </li>
                    )}
                {/* {links.map(link => {
                    return (
                        <li className={active === link.text ? activeClass : ""}>
                            <Link href= {link.href}>{link.text}</Link>
                        </li>
                    )
                })} */}

                {/* <li className={active === "dashboard"? activeClass : ""}>
                    <Link href="/dashboard">dashboard</Link>
                </li>
                <li className={active === "movimentações"? activeClass : ""}>
                    <Link href="/transactions">movimentações</Link>
                </li>
                <li className={active === "categorias"? activeClass : ""}>
                    <Link href="/categories">categorias</Link>
                </li> */}
            </ul>
            <img className="size-12 rounded-full" src="http://github.com/Felipe-2833.png" alt="Foto user" />
        </nav>
    )
}